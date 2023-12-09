(ns day09
  (:require
    [clojure.string :as str]))

(defn solve-line
  [+or- first-or-last line]
  (loop [prev-numbers (mapv parse-long (str/split line #" "))
         result []]
    (if (every? #(= 0 %) prev-numbers)
      (reduce
        (fn [current-numbers prev-number]
          (conj current-numbers (+or- prev-number (last current-numbers)))
          )
        [0]
        (reverse (map first-or-last (conj result prev-numbers))))
      (recur (map #(reduce - (reverse %)) (partition 2 1 prev-numbers)) (conj result prev-numbers)))
    )
  )

(defn part1
  [input]
  (transduce
    (comp
      (map (partial solve-line + last))
      (map last))
    +
    (str/split-lines input))
  )

(defn part2
  [input]
  (transduce
    (comp
      (map (partial solve-line - first))
      (map last))
    +
    (str/split-lines input))
  )