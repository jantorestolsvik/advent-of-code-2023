(ns day02
  (:require
    [clojure.edn :as edn]
    [clojure.string :as str]))

(defn parse-line [line]
  (let [[_ id games] (re-find #"Game (\d*): (.*)" line)
        state (atom {:id (edn/read-string id) "green" 0 "blue" 0 "red" 0})]
    (doseq [pickup (str/split games #";")
            number-color (str/split pickup #",")]

      (let [[number color] (str/split (str/trim number-color) #" ")]
        (swap!
          state
          #(update % color max (edn/read-string (str number)))))
      )
    @state
    )
  )

(defn part1
  [input]
  (transduce
    (comp
      (map parse-line)
      (filter (fn [game]
                (and
                  (>= 12 (game "red"))
                  (>= 13 (game "green"))
                  (>= 14 (game "blue"))
                  )
                ))
      (map :id))
    +
    (str/split-lines input)
    )
  )

(defn part2
  [input]
  (transduce
    (comp
      (map parse-line)
      (map (fn [game]
             (*
               (game "red")
               (game "green")
               (game "blue")
               ))))
    +
    (str/split-lines input)
    )
  )