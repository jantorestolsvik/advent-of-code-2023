(ns day07
  (:require
    [clojure.string :as str]))

(defn rank-hand
  [hand]
  (let [number-of-equals (vals (frequencies hand))]
    (cond
      (some #{5} number-of-equals) 10
      (some #{4} number-of-equals) 9
      (= [2 3] (sort number-of-equals)) 8
      (some #{3} number-of-equals) 7
      (= [1 2 2] (sort number-of-equals)) 6
      (= [1 1 1 2] (sort number-of-equals)) 5
      (= [1 1 1 1 1] (sort number-of-equals)) 4
      )))

(defn rank-hand-2
  [hand]
  (let [raw-frequencies (frequencies hand)
        number-of-equals (reverse (sort (vals (dissoc raw-frequencies \J))))
        number-of-equals (cons (+ (or (first number-of-equals) 0) (raw-frequencies \J 0)) (rest number-of-equals))]
    (cond
      (some #{5} number-of-equals) 10
      (some #{4} number-of-equals) 9
      (= [3 2] number-of-equals) 8
      (some #{3} number-of-equals) 7
      (= [2 2 1] number-of-equals) 6
      (= [2 1 1 1] number-of-equals) 5
      (= [1 1 1 1 1] number-of-equals) 4
      )))

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

(defn part1
  [input]
  (reduce
    +
    (map-indexed
      (fn [i [_ _ bid]]
        (* (inc i) (parse-long bid))
        )
      (sort (map
              (comp
                (fn [[hand bid]]
                  [(rank-hand hand) (mapv value hand) bid]
                  )
                #(str/split % #" "))
              (str/split-lines input)))))
  )

(defn part2
  [input]
  (reduce
    +
    (map-indexed
      (fn [i [_ _ bid]]
        (* (inc i) (parse-long bid))
        )
      (sort (map
              (comp
                (fn [[hand bid]]
                  [(rank-hand-2 hand) (mapv (assoc value \J 0) hand) bid]
                  )
                #(str/split % #" "))
              (str/split-lines input)))))
  )