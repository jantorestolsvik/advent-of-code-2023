(ns day04-test
  (:require [clojure.test :as t]
            [day04 :refer :all]))

(def puzzle-data (slurp "resources/day04.txt"))

(t/deftest parse-line-test
  (t/is
    (=
      {:cards-I-have  ["83"
                       "86"
                       "6"
                       "31"
                       "17"
                       "9"
                       "48"
                       "53"]
       :id            1
       :winning-cards ["41"
                       "48"
                       "83"
                       "86"
                       "17"]}
      (parse-line "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53"))
    )
  )

(t/deftest part-1-test
  (t/is (= 13 (part1 "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11")))
  (t/is (= 26914 (part1 puzzle-data)))
  )

(t/deftest part-2-test
  (t/is (= 30 (part2 "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11")))
  (t/is (= 13080971 (part2 puzzle-data))))
