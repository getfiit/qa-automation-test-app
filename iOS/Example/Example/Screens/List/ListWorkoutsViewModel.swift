//
//  ListWorkoutsViewModel.swift
//  Example
//
//  Created by Raffaele Cerullo on 23/05/2021.
//

import Foundation

protocol ListWorkoutsViewModelProtocol {
    var numberOfWorkouts: Int { get }
    var screenTitle: String { get }
    func cellViewModelAtIndexPath(_ indexPath: IndexPath) -> WorkoutCellViewModelProtocol?
    func detailViewModelAtIndexPath(_ indexPath: IndexPath) -> DetailWorkoutViewModelProtocol?
    
}

struct ListWorkoutsViewModel: ListWorkoutsViewModelProtocol {
    
    private var list: Workouts
    
    init() {
        let filePath = Bundle.main.url(forResource: "workouts", withExtension: "json")!
        let data = try! Data(contentsOf: filePath)
        list = try! JSONDecoder().decode(Workouts.self, from: data)
    }
    
    var numberOfWorkouts: Int { list.workouts.count }
    var screenTitle: String { "Workouts" }
    
    func cellViewModelAtIndexPath(_ indexPath: IndexPath) -> WorkoutCellViewModelProtocol? {
        guard indexPath.row < list.workouts.count else { return nil }
        let workout = list.workouts[indexPath.row]
        return WorkoutCellViewModel(with: workout)
    }
    
    func detailViewModelAtIndexPath(_ indexPath: IndexPath) -> DetailWorkoutViewModelProtocol? {
        guard indexPath.row < list.workouts.count else { return nil }
        let workout = list.workouts[indexPath.row]
        return DetailWorkoutViewModel(with: workout)
    }
}
