//
//  SubIconView.swift
//  issue-tracker
//
//  Created by SONG on 2023/05/16.
//

import UIKit

class SubIconView: UIImageView {

    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.image = image?.withTintColor(.gray, renderingMode: .alwaysOriginal)
    }

    func change(isCheckmarked: Bool) {
        if isCheckmarked == true {
            self.image = UIImage(systemName: "checkmark.circle.fill")
            self.image = image?.withTintColor(.tintColor, renderingMode: .alwaysOriginal)
        }
        else {
            self.image = UIImage(systemName: "chevron.forward")
            self.image = image?.withTintColor(.gray, renderingMode: .alwaysOriginal)
        }
    }
}
