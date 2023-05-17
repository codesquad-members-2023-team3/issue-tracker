//
//  IssueCollectionViewCell.swift
//  issue-tracker
//
//  Created by SONG on 2023/05/10.
//

import UIKit
import SwipeCellKit

class IssueCollectionViewCell: SwipeCollectionViewCell {
    static let identifier = "IssueCollectionViewCell"
    @IBOutlet var title: UILabel!
    @IBOutlet var contents: UILabel!
    @IBOutlet var milestoneImage: UIImageView!
    @IBOutlet var milestone: UILabel!
    @IBOutlet var labelsStackView: UIStackView!
    
    let badges = ["sfgs","gggassfsdfg","adfqewersdfsqasd"]
    
    override func awakeFromNib() {
        super.awakeFromNib()
        setTitle()
        setStackView()
        setBadge()
    }
    private func setTitle() {
        title.text = "iOS 이슈트래커 개발"
    }
    
    private func setStackView() {
        labelsStackView.axis = .horizontal
        labelsStackView.spacing = 10
        labelsStackView.distribution = .fill
    }
    
    private func setBadge() {
        for item in badges {
            labelsStackView.addArrangedSubview(BadgeLabel(name: item))
        }
        labelsStackView.addArrangedSubview(UIView(frame: CGRect()))
    }
}

extension IssueCollectionViewCell: SwipeCollectionViewCellDelegate {
    func collectionView(_ collectionView: UICollectionView, editActionsForItemAt indexPath: IndexPath, for orientation: SwipeCellKit.SwipeActionsOrientation) -> [SwipeCellKit.SwipeAction]? {
        return nil
    }
}
