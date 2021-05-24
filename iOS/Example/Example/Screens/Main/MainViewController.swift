//
//  MainViewController.swift
//  Example
//
//  Created by Raffaele Cerullo on 23/05/2021.
//

import UIKit

class MainViewController: UIViewController {

    private let viewModel: MainViewModelProtocol = MainViewModel()
    
    @IBOutlet weak var email: UITextField!
    @IBOutlet weak var password: UITextField!
    @IBOutlet weak var login: UIButton!

    @IBAction func didChange(_ sender: Any) {
        checkEnableButton()
    }
    
    @IBAction func login(_ sender: Any) {
        guard viewModel.isCredentialsAvailable(username: email.text, password: password.text) else { return }
        let storyboard = UIStoryboard(name: "ListWorkouts", bundle:nil)
        let nextViewController = storyboard.instantiateViewController(withIdentifier: "ListWorkouts") as! ListWorkoutsViewcontroller
        self.navigationController?.pushViewController(nextViewController, animated: true)
    }
    
    private func checkEnableButton() {
        login.isEnabled = !(email.text?.isEmpty ?? true) && !(password.text?.isEmpty ?? true)
    }
}

extension MainViewController: UITextFieldDelegate {
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
}
