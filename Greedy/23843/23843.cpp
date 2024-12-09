#include<iostream>
#include<vector>
#include<algorithm>
#include<queue>
using namespace std;

int N,M;
vector<int> ts;
int socket[11];
int max_index;
int max_value;

int main(){

    cin >> N >> M;
    for(int i=0;i<N;i++){
        int a;
        cin >> a;
        ts.push_back(a);
    }

    sort(ts.begin(),ts.end(), greater<>());

    // for(int i=0;i<N;i++){
    //     cout << ts[i] << " ";
    // }
    // cout << "\n";

    priority_queue<int, vector<int>, greater<int>> pq;

    for(int i=0;i<M;i++){
        pq.push(0);
    }

    for(int i=0;i<N;i++){
        int value=pq.top();
        pq.pop();
        pq.push(value+ts[i]);
    }

    int answer=0;
    while(!pq.empty()){
        answer=pq.top();
        //cout << pq.top() << " ";
        pq.pop();
    }
    cout << answer << "\n";
}