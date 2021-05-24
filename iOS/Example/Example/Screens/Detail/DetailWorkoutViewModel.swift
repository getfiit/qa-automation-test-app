//
//  DetailWorkoutViewModel.swift
//  Example
//
//  Created by Raffaele Cerullo on 24/05/2021.
//

import Foundation

protocol DetailWorkoutViewModelProtocol {
    var urlImage: URL? { get }
    var title: String { get }
    var duration: String { get }
    var difficulty: String { get }
    var description: String { get }
}

struct DetailWorkoutViewModel: DetailWorkoutViewModelProtocol {
    
    private let workout: Workout
    
    init(with workout: Workout) {
        self.workout = workout
    }
    
    var urlImage: URL? {
        URL(string: workout.imageUrl)
    }
    
    var title: String {
        workout.name
    }
    
    var duration: String {
        workout.duration
    }
    
    var difficulty: String {
        workout.difficulty
    }
    
    var description: String {
        workout.description
    }
}

