//
//  UIImageView+LoadImage.swift
//  Example
//
//  Created by Raffaele Cerullo on 23/05/2021.
//

import Foundation
import UIKit

extension UIImageView {
    func load(url: URL?, completion: (() -> ())? = nil) {
        self.image = UIImage(named: "placeholder")
        guard let url = url else {
            completion?()
            return
        }
        DispatchQueue.global(qos: .background).async { [weak self] in
            if let data = try? Data(contentsOf: url) {
                if let image = UIImage(data: data) {
                    DispatchQueue.main.async {
                        self?.image = image
                        completion?()
                    }
                }
            }
        }
    }
}
