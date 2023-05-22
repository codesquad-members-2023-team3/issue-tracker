//
//  CollectionReusableView.swift
//  issue-tracker
//
//  Created by SONG on 2023/05/11.
//

import UIKit

class IssueCollectionViewHeaderCell: UICollectionReusableView {
    static let identifier = "IssueCollectionViewHeaderCell"

    @IBOutlet var title: UILabel!
    
    @IBOutlet var searchBar: UISearchBar!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        setTitle()
        setSearchBar()
    }
    
    private func setSearchBar() {
        searchBar.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            searchBar.bottomAnchor.constraint(equalTo: self.bottomAnchor),
            searchBar.leadingAnchor.constraint(equalTo: self.leadingAnchor),
            searchBar.trailingAnchor.constraint(equalTo: self.trailingAnchor),
            searchBar.heightAnchor.constraint(equalToConstant:  self.frame.height / 2.5)
        ])
    }
    
    private func setTitle() {
        title.font = UIFont.systemFont(ofSize: safeAreaLayoutGuide.owningView!.frame.height * 0.35, weight: .bold)
        
        title.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate([
            title.topAnchor.constraint(equalTo: self.topAnchor),
            title.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: self.frame.width * 14 / 343),
            title.trailingAnchor.constraint(equalTo: self.trailingAnchor),
            title.heightAnchor.constraint(equalToConstant: self.frame.height / 2)
        ])
    }
}
