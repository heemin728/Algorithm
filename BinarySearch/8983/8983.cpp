#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int M,N,L;
vector<int> shooter;
vector<pair<int,int>> animals;

int binary_search(int num){
    int idx = lower_bound(shooter.begin(),shooter.end(),num)-shooter.begin();

    if(idx >= shooter.size()){
        idx=shooter.size()-1;
        return abs(num-shooter[idx]);
    }
    if(idx == 0){
        return abs(shooter[idx]-num);
    }
    return min(abs(shooter[idx]-num), abs(num-shooter[idx-1]));
}

int main(){
    cin >> M >> N >> L;

    for(int i=0;i<M;i++){
        int a;
        cin >> a;
        shooter.push_back(a);
    }

    for(int i=0;i<N;i++){
        int x,y;
        cin >> x >> y;
        animals.push_back({x,y});
    }
    
    sort(shooter.begin(),shooter.end());
    sort(animals.begin(),animals.end());

    int answer=0;
    for(int i=0;i<N;i++){
        if(binary_search(animals[i].first) + animals[i].second <= L){
            answer++;
        }
    }
    cout << answer << "\n";
}