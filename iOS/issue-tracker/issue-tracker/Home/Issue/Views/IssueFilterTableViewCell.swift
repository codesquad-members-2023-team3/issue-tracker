//
//  TableViewCellTest.swift
//  issue-tracker
//
//  Created by 에디 on 2023/05/10.
//

import UIKit

class IssueFilterTableViewCell: UITableViewCell {

    @IBOutlet var filterOptionLabel: UILabel!
    @IBOutlet var togglableImageView: UIImageView!
    var isOptionSelected = false {
        willSet {
            togglableImageView.image = newValue ? selectedImage : deselectedImage
        }
    }
    var selectedImage: UIImage?
    var deselectedImage: UIImage?
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    func configureWith(optionName: String, selectedImage: UIImage?, deselectedImage: UIImage?) {
        filterOptionLabel.text = optionName
        self.selectedImage = selectedImage
        self.deselectedImage = deselectedImage
        togglableImageView.image = deselectedImage
    }
    
    func toggleSelecting() {
        isOptionSelected.toggle()
    }
}
