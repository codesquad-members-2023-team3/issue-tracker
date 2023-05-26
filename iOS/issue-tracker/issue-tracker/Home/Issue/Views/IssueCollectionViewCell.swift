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
    @IBOutlet var subIconView: SubIconView!
    
    var isCheckmarked: Bool = false
    
    var badges = ["sfgs","gggassfsdfg","adfqewersdfsqasd"]
    
    override func awakeFromNib() {
        super.awakeFromNib()
        setTitle()
        setStackView()
//        setBadge() : configure 메소드로 이동
        setContents()
        setMilestoneImage()
        setMilestone()
        setSubIconView()
    }
    private func setSubIconView() {
        subIconView.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate([
            subIconView.centerXAnchor.constraint(equalTo: self.trailingAnchor, constant: self.frame.height / -6),
            subIconView.centerYAnchor.constraint(equalTo: title.centerYAnchor),
            subIconView.heightAnchor.constraint(equalTo: self.heightAnchor, multiplier: 25 / 148),
            subIconView.widthAnchor.constraint(equalTo: subIconView.heightAnchor, multiplier: 0.7)
        ])
    }
    
    func updateSubIconViewConstraints() {
        NSLayoutConstraint.deactivate(subIconView.constraints)
        
        if isCheckmarked {
            NSLayoutConstraint.activate([
                subIconView.centerXAnchor.constraint(equalTo: self.trailingAnchor, constant: self.frame.height / -6),
                subIconView.centerYAnchor.constraint(equalTo: title.centerYAnchor),
                subIconView.heightAnchor.constraint(equalTo: subIconView.heightAnchor),
                subIconView.widthAnchor.constraint(equalTo: subIconView.heightAnchor)
            ])
        } else {
            NSLayoutConstraint.activate([
                subIconView.centerXAnchor.constraint(equalTo: self.trailingAnchor, constant: self.frame.height / -6),
                subIconView.centerYAnchor.constraint(equalTo: title.centerYAnchor),
                subIconView.heightAnchor.constraint(equalTo: self.heightAnchor, multiplier: 25 / 148),
                subIconView.widthAnchor.constraint(equalTo: subIconView.heightAnchor, multiplier: 0.7)
            ])
        }
    }

    override func prepareForReuse() {
        super.prepareForReuse()
        labelsStackView.arrangedSubviews.forEach { view in view.removeFromSuperview() }
    }
    
    private func setTitle() {
        title.text = "iOS 이슈트래커 개발"
        
        title.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate([
            title.topAnchor.constraint(equalTo: self.topAnchor),
            title.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: round(self.frame.width * 20 / 322)),
            title.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: round(-(self.frame.width * 30 / 322))),
            title.heightAnchor.constraint(equalToConstant: round(self.frame.height * 34 / 131))
        ])
    }
    
    private func setContents() {
        contents.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate([
            contents.heightAnchor.constraint(equalToConstant: round(self.frame.height * 30 / 131)),
            contents.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: round(self.frame.width * 20 / 322)),
            contents.trailingAnchor.constraint(equalTo: self.trailingAnchor),
            contents.topAnchor.constraint(equalTo: title.bottomAnchor )
        ])
    }
    
    private func setMilestoneImage() {
        milestoneImage.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate([
            milestoneImage.heightAnchor.constraint(equalToConstant: round(self.frame.height * 30 / 131.0)),
            milestoneImage.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: round(self.frame.width * 20.0 / 322.0)),
            milestoneImage.topAnchor.constraint(equalTo: contents.bottomAnchor),
            milestoneImage.widthAnchor.constraint(equalTo: milestoneImage.heightAnchor)
        ])
    }
    
    private func setMilestone() {
        milestone.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate([
            milestone.heightAnchor.constraint(equalToConstant: round(self.frame.height * 30 / 131)),
            milestone.leadingAnchor.constraint(equalTo: milestoneImage.trailingAnchor, constant: round(self.frame.width * 4 / 375)),
            milestone.trailingAnchor.constraint(equalTo: self.trailingAnchor),
            milestone.topAnchor.constraint(equalTo: contents.bottomAnchor )
        ])
    }
    
    private func setStackView() {
        labelsStackView.axis = .horizontal
        labelsStackView.spacing = 10
        labelsStackView.distribution = .fill
        
        labelsStackView.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate([
            labelsStackView.centerYAnchor.constraint(equalTo: self.centerYAnchor, constant: round(self.frame.height/2.6 )),
            labelsStackView.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: round(self.frame.width * 20 / 322)),
            labelsStackView.trailingAnchor.constraint(equalTo: self.trailingAnchor),
            labelsStackView.heightAnchor.constraint(equalToConstant: round(self.frame.height * 30 / 131))
        ])
    }
    
    private func setBadge() {
        for item in badges {
            labelsStackView.addArrangedSubview(BadgeLabel(name: item))
        }
        labelsStackView.addArrangedSubview(UIView(frame: CGRect()))
    }
    
    func configure(with issueFrame: IssueFrame) {
        title.text = issueFrame.title
        contents.text = issueFrame.contents
        milestone.text = issueFrame.milestone?.name
        badges = issueFrame.labels.map { $0.name }
        setBadge()
    }
}

extension IssueCollectionViewCell: SwipeCollectionViewCellDelegate {
    func collectionView(_ collectionView: UICollectionView, editActionsForItemAt indexPath: IndexPath, for orientation: SwipeCellKit.SwipeActionsOrientation) -> [SwipeCellKit.SwipeAction]? {
        return nil
    }
}
