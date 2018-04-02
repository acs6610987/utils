//
//  badreceivebuffersize.h
//  network
//
//  Created by Zhicong Huang on 02/04/18.
//  Copyright © 2018 Zhicong Huang. All rights reserved.
//

#ifndef badreceivebuffersize_h
#define badreceivebuffersize_h

namespace network{
    
    class BadReceiveBufferSize : public std::exception
    {
    public:
        const char* mWhat;
        std::uint64_t mLength;
        std::unique_ptr<char[]> mData;
        
        BadReceiveBufferSize(const char* what, std::uint64_t length, std::unique_ptr<char[]>&& data)
        :
        mWhat(what),
        mLength(length),
        mData(std::move(data))
        { }
    };

}
#endif /* badreceivebuffersize_h */
