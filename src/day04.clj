(ns day04
  (:require
    [clojure.edn :as edn]
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
  (transduce
    (comp
      (map parse-line)
      (map (fn [game]
             (let [value (count
                           (filter
                             (set (:winning-cards game))
                             (:cards-I-have game)))]
               (if (= 0 value)
                 0
                 (int (Math/pow 2 (dec value)))))
             )))
    +
    (str/split-lines input)
    )
  )

(defn part2
  [input]
  (->>
    (let [parsed-lines (map parse-line (str/split-lines input))]
      (reduce
        (fn [acc card]
          (let [value (count
                        (filter
                          (set (:winning-cards card))
                          (:cards-I-have card)))]
            (merge-with +
                        acc
                        (into {} (map
                                   (fn [i] [(+ (:id card) i) (acc (:id card))])
                                   (range 1 (+ value 1))))
                        )
            )
          )
        (into {} (map
                   (fn [{:keys [id]}] [id 1])
                   parsed-lines))
        parsed-lines
        ))
    vals
    (reduce +)
    )
  )