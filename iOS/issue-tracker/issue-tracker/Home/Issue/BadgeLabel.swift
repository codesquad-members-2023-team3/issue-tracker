//
//  Badge.swift
//  issue-tracker
//
//  Created by SONG on 2023/05/15.
//

import UIKit

class BadgeLabel: UILabel {

    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    init(name: String) {
        super.init(frame: .zero)
        set(name: name)

    }
    
    func set(name: String) {
        setContent(name: name)
        layoutSubviews()
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        
        guard let superview = self.superview else {
            return
        }
        
        self.clipsToBounds = true
        self.layer.cornerRadius = 15
        
        self.font = UIFont.systemFont(ofSize: superview.frame.height / 2.6, weight: .regular)
        
        let font = self.font
        let text = self.text ?? ""
        
        let attributes = [NSAttributedString.Key.font: font]
        let textSize = (text as NSString).size(withAttributes: attributes as [NSAttributedString.Key: Any])
        let textWidth = ceil(textSize.width)

        self.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate([
            self.widthAnchor.constraint(equalToConstant: textWidth + superview.frame.height),
            self.heightAnchor.constraint(equalTo: superview.heightAnchor, multiplier: 0.8),
            self.centerYAnchor.constraint(equalTo: superview.centerYAnchor)
        ])
    }
    
    private func setContent(name: String) {
        self.text = name
        self.textAlignment = .center
        switch name {
        case "sfgs":
            self.backgroundColor = UIColor(red: 0.992, green: 0.587, blue: 0.112, alpha: 1.0)
        case "gggassfsdfg":
            self.backgroundColor = UIColor(red: 1.0, green: 0.741, blue: 0.075, alpha: 1.0)
        case "adfqewersdfsqasd":
            self.backgroundColor = UIColor(red: 0.0, green: 0.478, blue: 1, alpha: 1.0)
        default:
            break
        }
        self.textColor = .white
    }

}
