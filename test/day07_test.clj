(ns day07-test
  (:require [clojure.test :as t]
            [day07 :refer :all]))

(def puzzle-data (slurp "resources/day07.txt"))

(t/deftest part-1-test
  (t/is (= 6440 (part1 "32T3K 765
T55J5 684
KK677 28
KTJJT 220
QQQJA 483")))
  (t/is (= 253910319 (part1 puzzle-data)))
  )

(t/deftest part-2-test
  (t/is (= 5905 (part2 "32T3K 765
T55J5 684
KK677 28
KTJJT 220
QQQJA 483")))
  (t/is (= 254083736 (part2 puzzle-data))))
