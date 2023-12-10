(ns day10-test
  (:require [clojure.test :as t]
            [day10 :refer :all]))

(def puzzle-data (slurp "resources/day10.txt"))

(t/deftest part-1-test
  (t/is (= 8 (part1 "..F7.
.FJ|.
SJ.L7
|F--J
LJ...")))
  (t/is (= 6846 (part1 puzzle-data)))
  )

(t/deftest part-2-test
  (t/is (= 8 (count (part2 ".F----7F7F7F7F-7....
.|F--7||||||||FJ....
.||.FJ||||||||L7....
FJL7L7LJLJ||LJ.L-7..
L--J.L7...LJS7F-7L7.
....F-J..F7FJ|L7L7L7
....L7.F7||L7|.L7L7|
.....|FJLJ|FJ|F7|.LJ
....FJL-7.||.||||...
....L---J.LJ.LJLJ..."))))
  ;; TODO: Does not work . Can not tell if we are inside or outside the loop.
  ;; To fix I can count the number of times we turn left or right and use that do decide which direction to go.
  (t/is (= 325 (count (part2 puzzle-data)))))

;; too high 924
;; too low 212

;; 325

;; :N 325
;; :S crash