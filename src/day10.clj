(ns day10
  (:require
    [clojure.edn :as edn]
    [clojure.set :as set]
    [clojure.string :as str]))

(defn parse-line [line]
  (let [[_ id winning-cards cards-I-have] (re-find #"Card *(\d*): (.*) \| (.*)" line)]
    {:id            (edn/read-string id)
     :winning-cards (str/split (str/trim winning-cards) #" +")
     :cards-I-have  (str/split (str/trim cards-I-have) #" +")
     }

    )
  )

(defn part1
  [input]
  (/ (let [graph (mapv vec (str/split-lines input))
         S-position (first
                      (filter
                        #(not= -1 (second %))
                        (map-indexed
                          (fn [i line]
                            [i (.indexOf line \S)]
                            )
                          graph)))]
     (loop [pos (cond
                  (= (get-in graph (update S-position 0 inc)) \|) (update S-position 0 inc)
                  (= (get-in graph (update S-position 0 inc)) \L) (update S-position 0 inc))
            from :N
            length 1]
       (if (= pos S-position)
         length
         (case [(get-in graph pos) from]
           [\| :S] (recur (update pos 0 dec) :S (inc length))
           [\| :N] (recur (update pos 0 inc) :N (inc length))
           [\- :W] (recur (update pos 1 inc) :W (inc length))
           [\- :E] (recur (update pos 1 dec) :E (inc length))
           [\L :N] (recur (update pos 1 inc) :W (inc length))
           [\L :E] (recur (update pos 0 dec) :S (inc length))
           [\J :W] (recur (update pos 0 dec) :S (inc length))
           [\J :N] (recur (update pos 1 dec) :E (inc length))
           [\7 :S] (recur (update pos 1 dec) :E (inc length))
           [\7 :W] (recur (update pos 0 inc) :N (inc length))
           [\F :E] (recur (update pos 0 inc) :N (inc length))
           [\F :S] (recur (update pos 1 inc) :W (inc length))
           ))
       )
     ) 2)
  )

(defn part2
  [input]
  (let [graph (mapv vec (str/split-lines input))
        S-position (first
                     (filter
                       #(not= -1 (second %))
                       (map-indexed
                         (fn [i line]
                           [i (.indexOf line \S)]
                           )
                         graph)))
        start-pos (update S-position 0 inc)
        start-from :N
        pipes (loop [pos start-pos
                     from start-from
                     length 1
                     pipes #{S-position}]
                (if (= pos S-position)
                  pipes
                  (case [(get-in graph pos) from]
                    [\| :S] (recur (update pos 0 dec) :S (inc length) (conj pipes pos))
                    [\| :N] (recur (update pos 0 inc) :N (inc length) (conj pipes pos))
                    [\- :W] (recur (update pos 1 inc) :W (inc length) (conj pipes pos))
                    [\- :E] (recur (update pos 1 dec) :E (inc length) (conj pipes pos))
                    [\L :N] (recur (update pos 1 inc) :W (inc length) (conj pipes pos))
                    [\L :E] (recur (update pos 0 dec) :S (inc length) (conj pipes pos))
                    [\J :W] (recur (update pos 0 dec) :S (inc length) (conj pipes pos))
                    [\J :N] (recur (update pos 1 dec) :E (inc length) (conj pipes pos))
                    [\7 :S] (recur (update pos 1 dec) :E (inc length) (conj pipes pos))
                    [\7 :W] (recur (update pos 0 inc) :N (inc length) (conj pipes pos))
                    [\F :E] (recur (update pos 0 inc) :N (inc length) (conj pipes pos))
                    [\F :S] (recur (update pos 1 inc) :W (inc length) (conj pipes pos))
                    ))
                )
        non-pipe-starters (loop [pos start-pos
                                 from start-from
                                 neighbours #{}]
                            (if (= pos S-position)
                              (set/difference neighbours pipes)
                              (case [(get-in graph pos) from]
                                [\| :S] (recur (update pos 0 dec) :S (conj neighbours (update pos 1 dec)))
                                [\| :N] (recur (update pos 0 inc) :N (conj neighbours (update pos 1 inc)))
                                [\- :W] (recur (update pos 1 inc) :W (conj neighbours (update pos 0 dec)))
                                [\- :E] (recur (update pos 1 dec) :E (conj neighbours (update pos 0 inc)))
                                [\L :N] (recur (update pos 1 inc) :W neighbours)
                                [\L :E] (recur (update pos 0 dec) :S (conj neighbours (update pos 0 inc) (update pos 1 dec)))
                                [\J :W] (recur (update pos 0 dec) :S neighbours)
                                [\J :N] (recur (update pos 1 dec) :E (conj neighbours (update pos 1 inc) (update pos 0 inc)))
                                [\7 :S] (recur (update pos 1 dec) :E neighbours)
                                [\7 :W] (recur (update pos 0 inc) :N (conj neighbours (update pos 0 dec) (update pos 1 inc)))
                                [\F :E] (recur (update pos 0 inc) :N neighbours)
                                [\F :S] (recur (update pos 1 inc) :W (conj neighbours (update pos 1 dec) (update pos 0 dec)))
                                ))
                            )
        ]
    (loop [non-pipes non-pipe-starters
           to-look-at non-pipe-starters]
      (let [pos (first to-look-at)]
        (if (nil? pos)
          non-pipes
          (recur
            (if (or (pipes pos) (non-pipes pos))
              non-pipes
              (conj non-pipes pos))
            (set/union (disj to-look-at pos) (set (filter #(not (or (pipes %) (non-pipes %))) [(update pos 0 inc) (update pos 0 dec) (update pos 1 inc) (update pos 1 dec)]))))
          ))

      )

    )
  )