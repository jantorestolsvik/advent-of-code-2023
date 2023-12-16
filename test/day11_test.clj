(ns day11-test
  (:require [clojure.test :as t]
            [day11 :refer :all]))

(def puzzle-data (slurp "resources/day11.txt"))

(t/deftest part-1-test
  (t/is (= 374 (part1 "...#......
.......#..
#.........
..........
......#...
.#........
.........#
..........
.......#..
#...#.....")))
  (t/is (= 9370588 (part1 puzzle-data)))
  )

(t/deftest part-2-test
  (t/is (= 30 (part2 "")))
  (t/is (= 13080971 (part2 puzzle-data))))
