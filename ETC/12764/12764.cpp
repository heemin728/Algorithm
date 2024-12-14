#include<iostream>
#include<vector>
#include<algorithm>
#include<queue>
#define MAX 100001
using namespace std;

struct compare{
    bool operator()(pair<int,int> &p1, pair<int,int>p2){
        return p1.first > p2.first;
    }
};
struct compare2{
    bool operator()(pair<int,int> &p1, pair<int,int>p2){
        return p1.second > p2.second;
    }
};

int main(){
    int N;
    cin >> N;

    vector<pair<int,int>> v;
    priority_queue<pair<int,int>,vector<pair<int,int>>,greater<pair<int,int>>> pq0;

    for(int i=0;i<N;i++){
        int P,Q;
        cin >> P >> Q;
        pq0.push({P,Q});
    }

    // < 끝나는 시간, 인덱스 > 
    priority_queue<pair<int,int>,vector<pair<int,int>>, compare> pq;
    vector<int> times(MAX);

    int X=1;
    int index=0;
    times[0]=1;
    pair<int,int> init = pq0.top();
    pq0.pop();
    pq.push({init.second, index});
    priority_queue<pair<int,int>,vector<pair<int,int>>, compare2> saves;


    while(!pq0.empty()){
        pair<int,int> cur = pq0.top();
        pq0.pop();
        int start=cur.first;
       // cout << cur.first << ", " << cur.second << "\n";

        // 가능한 경우를 전부 탐색해보기
        // < 끝나는 시간, 인덱스 >
    
        while(!pq.empty() && pq.top().first <= start){
            saves.push(pq.top());
            //cout  << pq.top().first << " < " << start << "\n";
            pq.pop();
        }

        // 불가능
        if(saves.empty()){
            times[++index]++;
            //cout <<"1. " <<  cur.second << ", " << index << "\n";
            pq.push({cur.second, index});
        }
        else{
            int idx=saves.top().second;
            // if(idx==4){
            //    // cout << "top = " << saves.top().first << "\n";
            //     cout << saves.size() << "\n";
            // }
            saves.pop();

            //cout << "2. " << cur.second << ", " << idx << "\n";
            pq.push({cur.second,idx});
            times[idx]++;
        }

        // while(!saves.empty()){
        //     pq.push(saves.top());
        //     saves.pop();
        // }
    }

    cout << index+1 << "\n";

    for(int i=0;i<=index;i++){
        cout << times[i] << " ";
    }
    cout << "\n";
}

/*
1. 10, 2
1. 17, 3
1. 13, 4
1. 15, 5
10 < 14
13 < 14
2. 25, 2
15 < 16
2. 30, 4

*/