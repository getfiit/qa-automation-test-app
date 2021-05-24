//
//  DetailWorkoutViewController.swift
//  Example
//
//  Created by Raffaele Cerullo on 24/05/2021.
//

import Foundation
import UIKit

class DetailWorkoutViewController: UIViewController {
    
    var viewModel: DetailWorkoutViewModelProtocol!
    
    @IBOutlet weak var cover: UIImageView!
    @IBOutlet weak var difficulty: UILabel!
    @IBOutlet weak var duration: UILabel!
    @IBOutlet weak var desc: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        title = viewModel.title
        cover.load(url: viewModel.urlImage)
        difficulty.text = viewModel.difficulty
        duration.text = viewModel.duration
        desc.text = viewModel.description
    }
    
}
