(ns day09-test
  (:require [clojure.test :as t]
            [day09 :refer :all]))

(def puzzle-data (slurp "resources/day09.txt"))

(t/deftest part-1-test
  (t/is (= 114 (part1 "0 3 6 9 12 15
1 3 6 10 15 21
10 13 16 21 30 45")))
  (t/is (= 1969958987 (part1 puzzle-data)))
  )

(t/deftest part-2-test
  (t/is (= 2 (part2 "0 3 6 9 12 15
1 3 6 10 15 21
10 13 16 21 30 45")))
  (t/is (= 1068 (part2 puzzle-data))))
