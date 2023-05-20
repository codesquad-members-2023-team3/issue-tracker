//
//  IssueTabViewController.swift
//  issue-tracker
//
//  Created by SONG on 2023/05/09.
//

import UIKit
import OSLog

class IssueTabViewController: UIViewController {
    let logger = Logger()
    let fetcher = HTTPDataFetcher()
    let networkManager = NetworkManager()
    var issueFrames: [IssueFrame]?
    
    let collectionView = IssueCollectionView(frame: .zero, collectionViewLayout: UICollectionViewFlowLayout())
    @IBOutlet var backPlane: UIView!
    override func viewDidLoad() {
        super.viewDidLoad()
        backPlane.addSubview(collectionView)
        networkManager.fetchIssueData { result in
            switch result {
            case .success(let issueFrameHolder):
                self.issueFrames = issueFrameHolder.issues
            case .failure(let error):
                self.logger.error("error : \(error)")
            }
        }
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let navigationController = segue.destination as? UINavigationController{
            let filterTableViewController = navigationController.topViewController as? IssueFilterTableViewController
        }
    }
}
