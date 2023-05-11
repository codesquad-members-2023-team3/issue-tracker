//
//  HeaderTitleLabel.swift
//  issue-tracker
//
//  Created by SONG on 2023/05/10.
//

import UIKit

class HeaderTitleLabel: UILabel {

    override init(frame: CGRect) {
        super.init(frame: frame)
    }
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    func setView() {
        guard let superview = superview else {
            return
        }
    }
}
