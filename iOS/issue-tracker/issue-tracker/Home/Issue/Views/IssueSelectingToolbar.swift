//
//  IssueSelectingToolbar.swift
//  issue-tracker
//
//  Created by SONG on 2023/05/24.
//

import UIKit

class IssueSelectingToolbar: UIToolbar {
    private var selectedCellCount = 0
    private var rightItem: UIBarButtonItem? = nil
    private var leftItem: UIBarButtonItem? = nil
    private let flexibleSpace = UIBarButtonItem(barButtonSystemItem: .flexibleSpace, target: nil, action: nil)
    private var titleItem = UIBarButtonItem(title: "이슈를 선택하세요.", style: .done, target: nil, action: nil)
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setToolBarProPerties()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setToolBarProPerties()
    }
    
    private func setToolBarItems() -> [UIBarButtonItem] {
        rightItem = UIBarButtonItem(image: UIImage(systemName: "archivebox"), style: .plain, target: nil, action: nil)
        //TODO: 이슈 선택 개수에 따라 title 변경 기능
        titleItem.isEnabled = false
        leftItem = UIBarButtonItem(image: UIImage(systemName: "checkmark.circle"), style: .plain, target: nil, action: nil)
        
        guard let rightItem = self.rightItem else {
            return []
        }
        guard let leftItem = self.leftItem else {
            return []
        }
        
        return([leftItem, flexibleSpace, titleItem, flexibleSpace, rightItem])
    }
    
    func updateTitle(isPlus: Bool) {
        if isPlus == true {
            self.selectedCellCount += 1
            self.titleItem.title = "\(self.selectedCellCount)개의 이슈가 선택됨"
            titleItem.isEnabled = true
        }
        else if isPlus == false && self.selectedCellCount > 1 {
            self.selectedCellCount -= 1
            self.titleItem.title = "\(self.selectedCellCount)개의 이슈가 선택됨"
            titleItem.isEnabled = true
        }
        else if isPlus == false && self.selectedCellCount == 1 {
            self.selectedCellCount -= 1
            self.titleItem.title = "이슈를 선택하세요."
            titleItem.isEnabled = false
        }
        else if isPlus == false && self.selectedCellCount < 1 {
            self.titleItem.title = "이슈를 선택하세요."
            titleItem.isEnabled = false
        }
        
        guard let rightItem = self.rightItem else {
            return
        }
        guard let leftItem = self.leftItem else {
            return
        }
        
        self.setItems([leftItem, flexibleSpace, titleItem, flexibleSpace, rightItem], animated: true)
    }
    
    func resetTitle(){
        self.titleItem.title = "이슈를 선택하세요."
        titleItem.isEnabled = false
        self.selectedCellCount = 0
    }
    
    private func setToolBarProPerties() {
        self.items = setToolBarItems()
        self.isHidden = true
        self.barTintColor = .white
    }
}
