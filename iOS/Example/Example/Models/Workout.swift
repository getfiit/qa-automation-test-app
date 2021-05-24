//
//  Workout.swift
//  Example
//
//  Created by Raffaele Cerullo on 23/05/2021.
//

import Foundation

struct Workout: Codable {
    let id: Int
    let name: String
    let duration: String
    let difficulty: String
    let description: String
    let imageUrl: String
}
