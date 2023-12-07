(ns day06
  (:require
    [clojure.string :as str]))

(defn calc-distance
  [time]
  (fn [button-time] (* button-time (- time button-time)))
  )

(defn calc-game
  [[time distance]]
  (count (filter
           (partial < distance)
           (map
             (calc-distance time)
             (range time))))
  )

(defn part1
  [input]
  (let [games (apply
                map
                vector
                (map
                  (comp
                    #(map parse-long %)
                    #(re-seq #"\d+" %))
                  (str/split-lines input)))]
    (transduce
      (map calc-game)
      *
      games
      ))
  )

(defn part2
  [input]
  (let [games [(map
                 parse-long
                 (map
                   (comp
                     #(apply str %)
                     #(re-seq #"\d+" %))
                   (str/split-lines input)))]]
    (transduce
      (map calc-game)
      *
      games
      ))
  )