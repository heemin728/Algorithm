#include<iostream>
#include<algorithm>
#include<vector>
#define MAX 1001
using namespace std;

int N;
vector<pair<int,int>> v;
int depth[MAX];

bool cmp(pair<int,int> &p1, pair<int,int> &p2){
    if(p1.first != p2.first){
        return p1.first < p2.first;
    }
    return p1.second > p2.second;
}

int main(){
    cin >> N;
    for(int i=0;i<N;i++){
        int S,E;
        cin>> S>> E;
        v.push_back({S,E});
    }

    sort(v.begin(),v.end(), cmp);

    int max_index=v[0].second;
    int width=v[0].second-v[0].first+1;
    for(int i=v[0].first; i<=v[0].second; i++){
        depth[i]=1;
    }
    int height = 1;
    int answer=0;

    for(int i=1;i<N;i++){
        if(v[i].first <= max_index+1){
            if(v[i].second > max_index){
                width+=(v[i].second-max_index);
                max_index = v[i].second;
            }
            for(int j=v[i].first; j<=v[i].second;j++){
                depth[j]++;
                height=max(height,depth[j]);
            }
        }
        else{
            answer += (width*height);
            max_index=v[i].second;
            width = v[i].second - v[i].first +1;
            for(int j=v[i].first;j<=v[i].second;j++){
                depth[j]=1;
            }
            height=1;
        }
    }
    answer += (width * height);
    cout << answer << "\n";
}