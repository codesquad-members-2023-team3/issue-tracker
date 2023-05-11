//
//  IssueCollectionViewCell.swift
//  issue-tracker
//
//  Created by SONG on 2023/05/10.
//

import UIKit

class IssueCollectionViewCell: UICollectionViewCell {
    static let identifier = "Cell"
    @IBOutlet var title: UILabel!
    @IBOutlet var contents: UILabel!
    @IBOutlet var milestoneImage: UIImageView!
    @IBOutlet var milestone: UILabel!
    @IBOutlet var badge: UILabel!
    override func awakeFromNib() {
        super.awakeFromNib()
        setTitle()
        setBadge()
    }
    private func setTitle() {
        title.text = "iOS 이슈트래커 개발"
    }
    private func setBadge() {
        badge.clipsToBounds = true
        badge.layer.cornerRadius = 15
    }
}
