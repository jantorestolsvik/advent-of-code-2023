(ns day01
  (:require [clojure.string :as str]))

(def dict
  {"one" "1"
   "two" "2"
   "three" "3"
   "four" "4"
   "five" "5"
   "six" "6"
   "seven" "7"
   "eight" "8"
   "nine" "9"
   }
  )


(defn treat-line
  [line]
  (let [digits (re-seq #"\d" line)]
    (Integer/parseInt
      (reduce
      str
      [(first digits)
       (last digits)])))
  )

(defn treat-line-2
  [line]
  (let [numbers (map second (re-seq #"(?=(one|two|three|four|five|six|seven|eight|nine|\d))" line))]
    (Integer/parseInt
      (reduce
      str
      (map
        #(or (dict %) %)
        [(first numbers)
         (last numbers)])
      )))
  )

(defn part1
  [input]
  (reduce
    +
    (map
    treat-line
    (str/split-lines input)))
  )

(defn part2
  [input]
  (reduce
    +
    (map
    treat-line-2
    (str/split-lines input)))
  )