(ns day05
  (:require
    [clojure.string :as str]))

(defn parse-map
  [map-raw]
  (let [[first-line & values] (str/split-lines map-raw)]
    [first-line
     (map
       (fn [value]
         (let [[destination source length] (map parse-long (str/split value #" "))]
           [[source (+ source (dec length))] destination])
         )
       values)]
    )
  )

(defn source->destination
  [ranges value]
  (let [range (first (filter
                       (fn [[[from to]]]
                         (and (<= from value) (>= to value))
                         )
                       ranges))]
    (if range
      (+ (second range) (- value (first (first range))))
      value
      ))
  )

(defn source->destination-2
  [ranges intervals]
  (mapcat
    (fn [[from-value to-value]]
      (loop [from-value from-value
             to-value to-value
             result []]
        (let [range (first (filter
                             (fn [[[from to]]]
                               (and (<= from from-value) (>= to from-value))
                               )
                             ranges))]
          (if range
            (let [[[from to] start] range]
              (if (> to-value to)
                (recur (inc to) to-value (conj result [(+ start (- from-value from)) (+ (+ start (- from-value from)) (- to from-value))]))
                (conj result [(+ start (- from-value from)) (+ (+ start (- from-value from)) (- to-value from-value))]))
              )
            (let [next-range (first (sort-by
                                      #(-> % first first)
                                      (filter
                                        (fn [[[from to]]]
                                          (and
                                            (> from from-value)
                                            (<= from to-value)
                                            )
                                          )
                                        ranges)))]
              (if next-range
                (recur (first (first next-range)) to-value (conj result [from-value (dec (first (first next-range)))]))
                (conj result [from-value to-value]))
              )
            ))))
    intervals
    )
  )

(defn part1
  [input]
  (reduce
    min
    (let [[seeds-raw & maps-raw] (str/split input #"\n\n")
          maps-map (into {} (map parse-map maps-raw))]
      (map
        (comp
          #(source->destination (maps-map "humidity-to-location map:") %)
          #(source->destination (maps-map "temperature-to-humidity map:") %)
          #(source->destination (maps-map "light-to-temperature map:") %)
          #(source->destination (maps-map "water-to-light map:") %)
          #(source->destination (maps-map "fertilizer-to-water map:") %)
          #(source->destination (maps-map "soil-to-fertilizer map:") %)
          #(source->destination (maps-map "seed-to-soil map:") %)
          )
        (map parse-long (str/split (second (re-find #"seeds: (.*)" seeds-raw)) #" "))
        )
      ))
  )

(defn part2
  [input]
  (reduce
    min
    (flatten (let [[seeds-raw & maps-raw] (str/split input #"\n\n")
                   maps-map (into {} (map parse-map maps-raw))]
               (->>
                 (map
                   (fn [[start length]] [start (+ start length)])
                   (partition 2 (map parse-long (str/split (second (re-find #"seeds: (.*)" seeds-raw)) #" "))))
                 (source->destination-2 (maps-map "seed-to-soil map:"))
                 (source->destination-2 (maps-map "soil-to-fertilizer map:"))
                 (source->destination-2 (maps-map "fertilizer-to-water map:"))
                 (source->destination-2 (maps-map "water-to-light map:"))
                 (source->destination-2 (maps-map "light-to-temperature map:"))
                 (source->destination-2 (maps-map "temperature-to-humidity map:"))
                 (source->destination-2 (maps-map "humidity-to-location map:"))
                 )
               )))
  )