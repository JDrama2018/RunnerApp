//
//  BaseService.swift
//  Runner
//
//  Created by David Tong on 6/22/17.
//  Copyright © 2017 David Tong. All rights reserved.
//

import UIKit
import Alamofire

class BaseService: NSObject {

    typealias completionBlock = (_ success : Bool, _ json : [String: Any]?, _ errorMsg : String?)->()
    
    static var shared = BaseService()
    let BASE_URL = ""
    
    func request ( parameters : [String: Any], method : HTTPMethod, completion : @escaping completionBlock) {
        
        Alamofire.request(BASE_URL, method: method, parameters: parameters,
                          encoding: JSONEncoding.default)
            .responseJSON { response in
                
                guard response.result.error == nil else {
                    completion(false, nil, response.result.error?.localizedDescription)
                    return
                }
                guard let json = response.result.value as? [String: Any] else {
                    completion(false, nil, response.result.error?.localizedDescription)
                    return
                }
                completion(true, json, nil)
        }
    }
}
