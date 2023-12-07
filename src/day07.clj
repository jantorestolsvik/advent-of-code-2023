(ns day07
  (:require
    [clojure.string :as str]))

(defn rank-frequencies
  [freq]
  (cond
    (some #{5} freq) 10
    (some #{4} freq) 9
    (= [3 2] freq) 8
    (some #{3} freq) 7
    (= [2 2 1] freq) 6
    (= [2 1 1 1] freq) 5
    (= [1 1 1 1 1] freq) 4
    )
  )

(def value {
            \2 2
            \3 3
            \4 4
            \5 5
            \6 6
            \7 7
            \8 8
            \9 9
            \T 10
            \J 11
            \Q 12
            \K 13
            \A 14
            })

(defn solve
  [input rank-hand]
  (reduce
    +
    (map-indexed
      (fn [i [_ bid]]
        (* (inc i) (parse-long bid))
        )
      (sort-by
        #(rank-hand (first %))
        (map
          #(str/split % #" ")
          (str/split-lines input)))))
  )

(defn part1
  [input]
  (solve
    input
    (fn [hand]
      [(rank-frequencies (reverse (sort (vals (frequencies hand))))) (mapv value hand)]
      ))
  )

(defn part2
  [input]
  (solve
    input
    (fn [hand]
      (let [raw-frequencies (frequencies hand)
            number-of-equals (reverse (sort (vals (dissoc raw-frequencies \J))))]
        [(rank-frequencies (cons (+ (or (first number-of-equals) 0) (raw-frequencies \J 0)) (rest number-of-equals))) (mapv (assoc value \J 0) hand)])
      ))
  )