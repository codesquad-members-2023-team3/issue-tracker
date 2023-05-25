//
//  IssueTabViewController.swift
//  issue-tracker
//
//  Created by SONG on 2023/05/09.
//

import UIKit
import OSLog

class IssueTabViewController: UIViewController {
    private var addIssueButton: AddIssueButton?
    private var toolBar: UIToolbar?
    @IBOutlet var filterButton: UIBarButtonItem!
    @IBOutlet var selectButton: UIBarButtonItem!
    @IBOutlet var collectionView: IssueCollectionView!
    var cancelButton: UIBarButtonItem?
    let nothingButton = UIBarButtonItem(title: "", style: .plain, target: nil, action: nil)
    var filterOptionList: FilterOptionsLike = FilterOptionListMock()
    
    let fetcher = HTTPDataFetcher()
    
    private let logger = Logger()
    private let networkManager = NetworkManager()
    private var issueFrames: [IssueFrame]?
    private var currentIssueDataUrlString: String = "http://3.38.73.117:8080/api-ios/issues"
    
    @IBOutlet var backPlane: UIView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setCancelButton()
        setToolbar()

        setAddIssueButton()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        fetchData()
    }
    
    private func setAddIssueButton() {
        addIssueButton = AddIssueButton(radius: self.view.frame.height * 56 / 666)
        guard let addIssueButton = addIssueButton else {
            return
        }
        self.view.addSubview(addIssueButton)
        setIssueAddButtonConstraints(button: addIssueButton)
    }
    
    private func setIssueAddButtonConstraints(button: UIButton) {
        
        let length = self.view.frame.height * 56 / 666
        
        button.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            button.heightAnchor.constraint(equalToConstant: length),
            button.widthAnchor.constraint(equalToConstant: length),
            button.bottomAnchor.constraint(equalTo: self.collectionView.bottomAnchor, constant: -length/2),
            button.trailingAnchor.constraint(equalTo: self.collectionView.trailingAnchor, constant: -length/2)
        ])
    }
    
    private func calculateToolBarFrame() -> CGRect {
        guard let tabBar = self.tabBarController?.tabBar else {
            return CGRect()
        }
        let frame = CGRect(origin: tabBar.frame.origin, size: CGSize(width: tabBar.frame.width, height: tabBar.frame.height/2))
        return frame
    }
    
    private func setToolbar() {
        self.toolBar = IssueSelectingToolbar(frame: calculateToolBarFrame())
        guard let toolBar = self.toolBar else {
            return
        }
        self.view.addSubview(toolBar)
    }
    
    private func setCancelButton() {
        cancelButton = UIBarButtonItem(title: "취소  ", style: .plain, target: self, action: #selector(cancelButtonTouched))
    }
    
    @IBAction func filterButtonTouched(_ sender: UIButton) {
        let filterTableViewController = IssueFilterTableViewController()
        filterTableViewController.delegate = self
        filterTableViewController.filterOptionList = filterOptionList
        show(filterTableViewController, sender: sender)
    }
    
    @IBAction func selectButtonTouched(_ sender: UIButton) {
        self.toolBar?.isHidden = false
        self.tabBarController?.tabBar.isHidden = true
        self.navigationItem.leftBarButtonItem = nothingButton
        self.navigationItem.rightBarButtonItem = cancelButton
    }
    
    @objc private func cancelButtonTouched() {
        self.toolBar?.isHidden = true
        self.tabBarController?.tabBar.isHidden = false
        self.navigationItem.leftBarButtonItem = filterButton
        self.navigationItem.rightBarButtonItem = selectButton
    }
    
    private func fetchData() {
        guard let url = URL(string: currentIssueDataUrlString) else {
            self.logger.log(
                "Invalie URL string : \(self.currentIssueDataUrlString)")
            return
        }
        networkManager.fetchIssueData(with: url) { result in
            switch result {
            case .success(let issueFrameHolder):
                self.issueFrames = issueFrameHolder.issues
                guard let issueFrames = self.issueFrames else { return }
                self.collectionView.issueFrames = issueFrames
                DispatchQueue.main.async {
                    self.collectionView.reloadData()
                }
            case .failure(let error):
                self.logger.error("error : \(error)")
            }
        }
    }
    
    func setUrlString(with urlString: String) {
        currentIssueDataUrlString = urlString
    }
}
