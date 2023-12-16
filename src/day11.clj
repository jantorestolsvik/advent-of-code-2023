(ns day11
  (:require
    [clojure.string :as str]))

(defn solve
  [input length]
  (let [lines (str/split-lines input)
        cols (keep
               (fn [i]
                 (when (every? #(= \. (get % i)) lines) i))
               (range (count (first lines))))
        rows (keep-indexed
               (fn [i line]
                 (when (every? #(= \. %) line) i))
               lines)
        nodes (apply concat (keep-indexed
                              (fn [i1 line]
                                (seq (keep-indexed
                                       (fn [i2 char] (when (= \# char)
                                                       [(+ i1 (* (dec length) (count (filter #(<= % i1) rows))))
                                                        (+ i2 (* (dec length) (count (filter #(<= % i2) cols))))]))
                                       line))
                                )
                              lines))
        distances (map
                    (fn [[x1 y1]]
                      (map
                        (fn [[x2 y2]]
                          (+ (Math/abs (- y2 y1))
                             (Math/abs (- x2 x1))
                             ))
                        nodes))
                    nodes)]

    (reduce + (for [i (range 0 (count distances))
                    j (range (inc i) (count distances))]
                (nth (nth distances i) j)
                ))

    )
  )

(defn part1
  [input]
  (solve input 1)
  )

(defn part2
  [input]
  (solve input 999999)
  )