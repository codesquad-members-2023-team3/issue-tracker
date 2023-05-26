//
//  IssueFrame.swift
//  issue-tracker
//
//  Created by 에디 on 2023/05/18.
//

import Foundation

struct IssueFrame: Codable {
    let number: Int
    let title: String
    let contents: String
    let state: Bool
    let createdDate: String
    let lastUpdatedDate: String
//    let author: String
    let milestone: Milestone?
    let user: User
    let labels: [Label]
    let assignees: [User]
}

struct User: Codable {
    let id: String
    let password: String
    let nickname: String
    let imgUrl: String
}

