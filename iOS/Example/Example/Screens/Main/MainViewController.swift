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
        guard viewModel.isCredentialsAvailable(username: email.text, password: password.text) else {
            displayError()
            return
        }
        let storyboard = UIStoryboard(name: "ListWorkouts", bundle:nil)
        let nextViewController = storyboard.instantiateViewController(withIdentifier: "ListWorkouts") as! ListWorkoutsViewcontroller
        self.navigationController?.pushViewController(nextViewController, animated: true)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        email.text = nil
        password.text = nil
        checkEnableButton()
    }
    
    private func checkEnableButton() {
        login.isEnabled = !(email.text?.isEmpty ?? true) && !(password.text?.isEmpty ?? true)
    }
    
    private func displayError() {
        let alertController = UIAlertController(title: "Error", message: "Wrong credentials", preferredStyle: .alert)
        let alertaction = UIAlertAction(title: "OK", style: .default, handler: nil)
        alertController.addAction(alertaction)
        self.present(alertController, animated: true, completion: nil)
    }
}

extension MainViewController: UITextFieldDelegate {
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
}
