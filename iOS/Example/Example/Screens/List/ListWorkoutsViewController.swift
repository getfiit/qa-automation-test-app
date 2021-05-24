//
//  ListWorkoutsViewController.swift
//  Example
//
//  Created by Raffaele Cerullo on 23/05/2021.
//

import Foundation
import UIKit

class ListWorkoutsViewcontroller: UITableViewController {

    private let viewModel: ListWorkoutsViewModelProtocol =  ListWorkoutsViewModel()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        title = viewModel.screenTitle
        tableView.rowHeight = 100
        navigationItem.setHidesBackButton(true, animated: true)
    }
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        title = viewModel.screenTitle
    }
    
    @IBAction func logout(_ sender: Any) {
        self.navigationController?.popToRootViewController(animated: true)
    }
}

extension ListWorkoutsViewcontroller {
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        viewModel.numberOfWorkouts
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "WorkoutCell", for: indexPath)
        let cellViewModel = viewModel.cellViewModelAtIndexPath(indexPath)
        cell.textLabel?.text = cellViewModel?.title
        cell.detailTextLabel?.text = cellViewModel?.subtitle
        cell.imageView?.image = nil
        cell.imageView?.load(url: cellViewModel?.urlImage)
        return cell
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let detailViewModel = viewModel.detailViewModelAtIndexPath(indexPath)
        displayDetailWorkout(with: detailViewModel)
    }
}

extension ListWorkoutsViewcontroller {
    private func displayDetailWorkout(with viewModel: DetailWorkoutViewModelProtocol?) {
        guard let viewModel = viewModel else { return }
        let storyboard = UIStoryboard(name: "DetailWorkout", bundle:nil)
        let nextViewController = storyboard.instantiateViewController(withIdentifier: "DetailWorkout") as! DetailWorkoutViewController
        nextViewController.viewModel = viewModel
        self.navigationController?.pushViewController(nextViewController, animated: true)
    }
}
