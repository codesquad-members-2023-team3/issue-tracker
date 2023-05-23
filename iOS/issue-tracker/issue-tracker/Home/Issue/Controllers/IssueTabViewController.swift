//
//  IssueTabViewController.swift
//  issue-tracker
//
//  Created by SONG on 2023/05/09.
//

import UIKit
import OSLog

class IssueTabViewController: UIViewController {
    private let logger = Logger()
    private let networkManager = NetworkManager()
    private var issueFrames: [IssueFrame]?
    private var currentIssueDataUrlString: String = "http://3.38.73.117:8080/api-ios/issues"
    
    let collectionView = IssueCollectionView(frame: .zero, collectionViewLayout: UICollectionViewFlowLayout())
    @IBOutlet var backPlane: UIView!
    override func viewDidLoad() {
        super.viewDidLoad()
        backPlane.addSubview(collectionView)
        // TODO: fetchData(with: String)로 대체 예정
        networkManager.fetchIssueData { result in
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
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let navigationController = segue.destination as? UINavigationController{
            let filterTableViewController = navigationController.topViewController as? IssueFilterTableViewController
            filterTableViewController?.delegate = self
        }
    }
    
    func fetchData() {
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
}
