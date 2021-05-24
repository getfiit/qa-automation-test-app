//
//  MainViewModel.swift
//  Example
//
//  Created by Raffaele Cerullo on 24/05/2021.
//

import Foundation

protocol MainViewModelProtocol {
    func isCredentialsAvailable(username: String?, password: String?) -> Bool
}

struct MainViewModel: MainViewModelProtocol {
    
    func isCredentialsAvailable(username: String?, password: String?) -> Bool {
        guard let username = username else { return false }
        return availableCredentials[username] == password
    }
    
    private let availableCredentials: [String: String] = {
        var dictionary = [String: String]()
        dictionary["username@fiit.tv"] = "longpassword"
        dictionary["login@fiit.tv"] = "verystrongpassword"
        return dictionary
    }()
    
}
