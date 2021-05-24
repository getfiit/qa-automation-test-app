//
//  WorkoutCellViewModel.swift
//  Example
//
//  Created by Raffaele Cerullo on 23/05/2021.
//

import Foundation

protocol WorkoutCellViewModelProtocol {
    var urlImage: URL? { get }
    var title: String { get }
    var subtitle: String { get }
}

struct WorkoutCellViewModel: WorkoutCellViewModelProtocol {
    
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
    
    var subtitle: String {
        workout.difficulty + " | " + workout.duration
    }
}
