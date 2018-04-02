#ifndef NETWORK_UTILS_H
#define NETWORK_UTILS_H

#include "boost_channel.h"
#include <vector>

namespace network
{

    void send_int(int data, Channel &channel);

    void receive_int(int &data, Channel &channel);

    void send_uint64(uint64_t data, Channel &channel);

    void receive_uint64(uint64_t &data, Channel &channel);

    void send_string(const std::string &data, Channel &channel);

    void receive_string(std::string &data, Channel &channel);
    
    std::vector<std::string> split(std::string s, char delim);
}

#endif