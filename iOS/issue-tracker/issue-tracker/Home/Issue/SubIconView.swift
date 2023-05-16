//
//  SubIconView.swift
//  issue-tracker
//
//  Created by SONG on 2023/05/16.
//

import UIKit

class SubIconView: UIImageView {
    
    override init(image: UIImage?) {
        super.init(image: image)
        self.image = image?.withTintColor(.gray, renderingMode: .alwaysOriginal)
        self.layoutSubviews()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        guard let superview = superview else {
            return
        }
        
        self.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate([
            self.heightAnchor.constraint(equalTo: superview.heightAnchor, multiplier: 16 / 148),
            self.widthAnchor.constraint(equalTo: self.heightAnchor, multiplier: 0.7),
            self.centerXAnchor.constraint(equalTo: superview.trailingAnchor, constant: superview.frame.height / -6),
            self.centerYAnchor.constraint(equalTo: superview.topAnchor, constant: superview.frame.height / 6)
        ])
    }
   
}
