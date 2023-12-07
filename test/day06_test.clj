(ns day06-test
  (:require [clojure.test :as t]
            [day06 :refer :all]))

(def puzzle-data (slurp "resources/day06.txt"))

(t/deftest calc-game-test
  (t/is (= 4 (calc-game [7 9])))
  #_(t/is (= 26914 (part1 puzzle-data)))
  )

(t/deftest part-1-test
  (t/is (= 288 (part1 "Time:      7  15   30
Distance:  9  40  200")))
  (t/is (= 138915 (part1 puzzle-data)))
  )

(t/deftest part-2-test
  (t/is (= 71503 (part2 "Time:      7  15   30
Distance:  9  40  200")))
  (t/is (= 27340847 (part2 puzzle-data))))
