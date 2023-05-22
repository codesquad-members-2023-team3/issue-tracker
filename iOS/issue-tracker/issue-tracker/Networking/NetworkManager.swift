//
//  NetworkManager.swift
//  issue-tracker
//
//  Created by 에디 on 2023/05/20.
//

import Foundation

class NetworkManager {
    func fetchIssueData(
        with url: URL,
        completion: @escaping (Result<IssueFrameHolder, Error>) -> Void) {
            let request = URLRequest(url: url, timeoutInterval: 3.0)
            
            URLSession.shared.dataTask(with: request) { (data, response, error) in
                if let error = error {
                    completion(.failure(error))
                }
                
                if let data = data {
                    do {
                        let issueFrames = try JSONDecoder().decode(IssueFrameHolder.self, from: data)
                        completion(.success(issueFrames))
                    } catch let decodeError {
                        completion(.failure(decodeError))
                    }
                }
            }.resume()
    }
    
    func fetchIssueData(completion: @escaping (Result<IssueFrameHolder, Error>) -> Void) {
        guard let url = URL(string: "http://3.38.73.117:8080/api-ios/issues") else { return }
        let request = URLRequest(url: url, timeoutInterval: 3.0)
        
        URLSession.shared.dataTask(with: request) { (data, response, error) in
            if let error = error {
                completion(.failure(error))
            }
            
            if let data = data {
                do {
                    let issueFrames = try JSONDecoder().decode(IssueFrameHolder.self, from: data)
                    completion(.success(issueFrames))
                } catch let decodeError {
                    completion(.failure(decodeError))
                }
            }
        }.resume() 
    }
}
