(ns day11
  (:require
    [clojure.string :as str]))

(defn part1
  [input]
  (let [lines (str/split-lines input)
        cols (set (keep
                    (fn [i]
                      (when (every? #(= \. (get % i)) lines) i))
                    (range (count (first lines)))))
        expanded (mapcat
                   (fn [line]
                     (let [new-line (str/join (flatten (map-indexed
                                                         (fn [i char]
                                                           (if (cols i)
                                                             [char char]
                                                             [char]))
                                                         line)))]
                       ;; Clojure how to add something in the middle of a string, or vector?
                       ;; Is this even necessary? Could just add to the length elsewise maybe
                       (if (every? #(= \. %) line)
                         [new-line new-line]
                         [new-line])))
                   lines)
        nodes (apply concat (keep-indexed
                              (fn [i1 line]
                                (seq (keep-indexed
                                       (fn [i2 char] (when (= \# char) [i1 i2]))
                                       line))
                                )
                              expanded))
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

(defn part2
  [input]

  )