#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#define MAX 100001
using namespace std;
 
int N;
vector<pair<int, int> > v;
priority_queue<pair<int, int> > pq; 
priority_queue<int> pq2; 
int times[MAX]; 
 
int main(void){
    cin >> N;
    
    int P,Q;
    for(int i=0; i<N; i++){
        cin >> P >> Q;
        v.push_back({P, Q});
    }
    
    sort(v.begin(), v.end());
    int index = 0;
    
    for(int i=0;i<v.size();i++){
        while(!pq.empty()){
            if(-pq.top().first <= v[i].first){
                pq2.push(-pq.top().second);
                pq.pop();
            }
            else{
                break;
            }
        }
        
        if(pq2.empty()){
            pq.push({-v[i].second, index});
            times[index++]++;
        }
        else{
            int idx = -pq2.top();
            pq.push({-v[i].second, idx});
            times[idx]++;
            pq2.pop();
        }
    }
    
    cout << index << "\n";
    for(int i=0; i<index; i++){
        cout << times[i] << " "; 
    }   
}
