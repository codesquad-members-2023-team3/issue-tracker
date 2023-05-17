//
//  IssueCollectionView.swift
//  issue-tracker
//
//  Created by SONG on 2023/05/10.
//

import UIKit
import SwipeCellKit

class IssueCollectionView: UICollectionView {
    override init(frame: CGRect, collectionViewLayout layout: UICollectionViewLayout) {
        super.init(frame: frame, collectionViewLayout: layout)
        self.delegate = self
        self.dataSource = self
        registerCell()
    }
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.delegate = self
        self.dataSource = self
        registerCell()
    }
    private func registerCell() {
        register(UINib(nibName: "IssueCollectionViewCell", bundle: nil), forCellWithReuseIdentifier: IssueCollectionViewCell.identifier)
        register(UINib(nibName: "IssueCollectionViewHeaderCell", bundle: nil), forSupplementaryViewOfKind: UICollectionView.elementKindSectionHeader, withReuseIdentifier: IssueCollectionViewHeaderCell.identifier)
    }
}

extension IssueCollectionView: UICollectionViewDelegate {
}

extension IssueCollectionView: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return 15
    }
    
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: IssueCollectionViewCell.identifier, for: indexPath) as? SwipeCollectionViewCell else {
            return UICollectionViewCell()
        }
        cell.delegate = self
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, willDisplay cell: UICollectionViewCell, forItemAt indexPath: IndexPath) {
        cell.backgroundColor = .white
    }
    
    func collectionView(_ collectionView: UICollectionView, viewForSupplementaryElementOfKind kind: String, at indexPath: IndexPath) -> UICollectionReusableView {
        switch kind {
        case UICollectionView.elementKindSectionHeader:
            guard let headerView = collectionView.dequeueReusableSupplementaryView(ofKind: kind, withReuseIdentifier: IssueCollectionViewHeaderCell.identifier, for: indexPath) as? IssueCollectionViewHeaderCell else {
                return UICollectionReusableView()
            }
            headerView.backgroundColor = .white
            return headerView
        default:
            return UICollectionReusableView()
        }
    }
}

extension IssueCollectionView: UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, insetForSectionAt section: Int) -> UIEdgeInsets {
        UIEdgeInsets(top: .zero, left: .zero, bottom: .zero, right: .zero)
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let width = collectionView.frame.width
        let height = width / 3
        return CGSize(width: width, height: height)
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, referenceSizeForHeaderInSection section: Int) -> CGSize {
        let width = self.frame.width
        let height = width / 4
        
        return CGSize(width: width, height: height)
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumLineSpacingForSectionAt section: Int) -> CGFloat {
        return 1.0
    }
}

extension IssueCollectionView: SwipeCollectionViewCellDelegate {
    func collectionView(_ collectionView: UICollectionView, editActionsForItemAt indexPath: IndexPath, for orientation: SwipeActionsOrientation) -> [SwipeAction]? {
        
        guard let superview = superview else {
            return nil
        }
        
        guard orientation == .right else {
            return nil
        }
        
        let delete = SwipeAction(style: .destructive, title: "삭제") { action, indexPath in }
        
        delete.image = UIImage(systemName: "trash")
        
        let exit = SwipeAction(style: .default, title: "닫기") { action, indexPath in }
        
        exit.image = UIImage(systemName: "archivebox")
        exit.backgroundColor = UIColor(red: 0.329, green: 0.227, blue: 0.745, alpha: 1)

        return [delete, exit]
    }
    
    func collectionView(_ collectionView: UICollectionView, editActionsOptionsForItemAt indexPath: IndexPath, for orientation: SwipeActionsOrientation) -> SwipeOptions {
        var options = SwipeOptions()
        options.expansionStyle = .destructive
        options.transitionStyle = .border
        return options
    }
}

