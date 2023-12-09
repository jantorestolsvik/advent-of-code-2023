(ns day08
  (:require
    [clojure.string :as str]))

(defn part1
  [input]
  (let [[instructions _ & row] (str/split-lines input)]
    (let [maps (into {}
                     (map
                       (fn [row] [(second row) {\L (nth row 2) \R (nth row 3)}])
                       (map #(re-find #"(.*) = \((.*), (.*)\)" %) row)))
          instructions (cycle (vec instructions))]

      (loop [item "AAA"
             instructions instructions
             count 1]
        (let [value (get-in maps [item (first instructions)])]
          (if (= value "ZZZ")
            count
            (recur value (rest instructions) (inc count))))))
    )
  )

(defn gcd [a b] (if (zero? b) a (recur b, (mod a b))))
(defn lcm [a b] (/ (* a b) (gcd a b)))
(defn lcmv [& v] (reduce lcm v))

(defn part2
  [input]
  (reduce lcm (let [[instructions _ & row] (str/split-lines input)]
                (let [maps (into {}
                                 (map
                                   (fn [row] [(second row) {\L (nth row 2) \R (nth row 3)}])
                                   (map #(re-find #"(.*) = \((.*), (.*)\)" %) row)))
                      instructions (cycle (vec instructions))]


                  (map
                    (fn [item]
                      (loop [item item
                             instructions instructions
                             count 0]
                        (if (str/ends-with? item "Z")
                          count
                          (recur (get-in maps [item (first instructions)]) (rest instructions) (inc count))
                          )
                        ))
                    (filterv #(str/ends-with? % "A") (keys maps))))
                ))
  )