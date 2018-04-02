//
//  main.cpp
//  network
//
//  Created by Zhicong Huang on 02/04/18.
//  Copyright © 2018 Zhicong Huang. All rights reserved.
//

#include <iostream>
#include <memory>
#include <thread>
#include "boost_ioservice.h"
#include "boost_endpoint.h"
#include "network_utils.h"

using namespace std;
using namespace network;

void test_simple_messenger(string app_name);
void test_server(bool& stop_signal, string app_name);
void test_client(string app_name);

int main(int argc, const char * argv[]) {
    
    test_simple_messenger("simple messenger");
    
    return 0;
}

void test_simple_messenger(string app_name)
{
    bool stop = false;
    
    thread server_thread(test_server, std::ref(stop), app_name);
    thread client_thread(test_client, app_name);
    
    cout << "Press any key to stop connection..." << endl;
    getchar();
    
    stop = true;
    server_thread.join();
    client_thread.join();

}

void test_server(bool& stop_signal, string app_name)
{
    unique_ptr<BoostIOService> ios(new BoostIOService(0));
    unique_ptr<BoostEndpoint> endpoint(new BoostEndpoint(*ios, "127.0.0.1", 8123, true, app_name));
    while(!stop_signal)
    {
        Channel* server_channel = endpoint->getNextQueuedChannel();
        if(server_channel == nullptr)
        {
            this_thread::sleep_for(chrono::milliseconds(50));
            continue;
        }
        send_string("hello, I'm server.", *server_channel);
        server_channel->close();
    }
    endpoint->stop();
}

void test_client(string app_name)
{
    unique_ptr<BoostIOService> ios(new BoostIOService(0));
    BoostEndpoint client(*ios, "127.0.0.1", 8123, false, app_name); // must use the same endpoint name as the server.
    Channel& client_channel = client.addChannel("-", "-");
    
    string data;
    receive_string(data, client_channel);
    cout << "Client: I receive '" << data << "'" << endl;
    
    client_channel.close();
    client.stop();
}