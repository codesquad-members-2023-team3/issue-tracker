//
//  Milestone.swift
//  issue-tracker
//
//  Created by SONG on 2023/05/18.
//

import Foundation

struct Milestone: Codable {
    let name: String
    let scheduledCompletionDate: String
    let descriptionForLabel: String
    let empty: Bool
}
