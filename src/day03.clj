(ns day03
  (:require
    [clojure.edn :as edn]
    [clojure.string :as str]))

(def digit #{\0 \1 \2 \3 \4 \5 \6 \7 \8 \9})

(defn symbol
  [char]
  (and (not (digit char))
       (not= \. char)
       (not (nil? char)))
  )

(defn part1
  [input]
  (transduce
    (comp
      (filter second)
      (map first)
      (map edn/read-string)
      )
    +
   (apply concat (let [lines (str/split-lines input)
                       columns (count (first lines))
                       rows (count lines)]
                   (for [i (range rows)
                         :let [line (nth lines i)]]
                     (doall (for [j (range columns)
                                  :let [x (nth line j)]
                                  :when (and (digit x)
                                             (not (digit (nth line (- j 1) nil))))]
                              (if (digit x)
                                (let [number (atom "")
                                      adjacent-symbol (atom false)]
                                  (doall (for [k (range j columns)
                                               :while (digit (nth line k))]
                                           (do
                                             (swap! number str (nth line k))
                                             (swap! adjacent-symbol
                                                    #(or
                                                       %
                                                       (symbol (nth (nth lines i nil) (- k 1) nil))
                                                       (symbol (nth (nth lines i nil) k nil))
                                                       (symbol (nth (nth lines i nil) (+ k 1) nil))
                                                       (symbol (nth (nth lines (+ i 1) nil) (- k 1) nil))
                                                       (symbol (nth (nth lines (+ i 1) nil) k nil))
                                                       (symbol (nth (nth lines (+ i 1) nil) (+ k 1) nil))
                                                       (symbol (nth (nth lines (- i 1) nil) (- k 1) nil))
                                                       (symbol (nth (nth lines (- i 1) nil) k nil))
                                                       (symbol (nth (nth lines (- i 1) nil) (+ k 1) nil)))
                                                    )
                                             )

                                           ))
                                  [@number @adjacent-symbol]
                                  )
                                nil)
                              ))
                     )
                   )))
  )

(defn part2
  [input]
  (transduce
      (comp
        (filter (fn [[_ matches]] (= 2 (count matches))))
        (map (fn [[_ matches]] (* (Integer/parseInt (first (first matches)))
                                  (Integer/parseInt (first (second matches)))
                                  )))
        )
      +
      (group-by second
                (apply concat (let [lines (str/split-lines input)
                                    columns (count (first lines))
                                    rows (count lines)]
                                (for [i (range rows)
                                      :let [line (nth lines i)]]
                                  (doall (for [j (range columns)
                                               :let [x (nth line j)]
                                               :when (and (digit x)
                                                          (not (digit (nth line (- j 1) nil))))]
                                           (if (digit x)
                                             (let [number (atom "")
                                                   gear (atom nil)]
                                               (doall (for [k (range j columns)
                                                            :while (digit (nth line k))]
                                                        (do
                                                          (swap! number str (nth line k))
                                                          (cond
                                                            (= \* (nth (nth lines i nil) (- k 1) nil)) (reset! gear (str i "-" (- k 1)))
                                                            (= \* (nth (nth lines i nil) k nil)) (reset! gear (str i "-" k))
                                                            (= \* (nth (nth lines i nil) (+ k 1) nil)) (reset! gear (str i "-" (+ k 1)))
                                                            (= \* (nth (nth lines (+ i 1) nil) (- k 1) nil)) (reset! gear (str (+ i 1) "-" (- k 1)))
                                                            (= \* (nth (nth lines (+ i 1) nil) k nil)) (reset! gear (str (+ i 1) "-" k))
                                                            (= \* (nth (nth lines (+ i 1) nil) (+ k 1) nil)) (reset! gear (str (+ i 1) "-" (+ k 1)))
                                                            (= \* (nth (nth lines (- i 1) nil) (- k 1) nil)) (reset! gear (str (- i 1) "-" (- k 1)))
                                                            (= \* (nth (nth lines (- i 1) nil) k nil)) (reset! gear (str (- i 1) "-" k))
                                                            (= \* (nth (nth lines (- i 1) nil) (+ k 1) nil)) (reset! gear (str (- i 1) "-" (+ k 1)))
                                                            )
                                                          )

                                                        ))
                                               [@number @gear]
                                               )
                                             nil)
                                           ))
                                  )
                                ))))
  )