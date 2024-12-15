#include<iostream>
#include<algorithm>
#include<vector>
#define MAX 1000001
using namespace std;

int N,M;
vector<int> locations;
vector<int> dist;

int main(){
    cin >> N >> M;

    for(int i=0;i<N;i++){
        int a;
        cin >> a;
        locations.push_back(a);
    }
    for(int i=0;i<N;i++){
        int a;
        cin >> a;
        dist.push_back(a);
    }

    int nidx=0;
    int nloc=1;
    if(nloc != 1){
        cout << "-1\n";
        return 0;
    }

    int cnt=0;

    while(nidx<N){  
        nloc=locations[nidx];       
        int end=nloc+dist[nidx];

        if(end >= M){
            break;
        }

        int nextIndex=nidx+1;
        int maxValue=0;
        bool possible=false;

        for(int i=nidx+1;i<N;i++){

            if(locations[i] <= end){
                if(locations[i]+dist[i] > maxValue){
                    if(locations[i]+dist[i] >= M){
                        cout << ++cnt << "\n";
                        return 0;
                    }
                    maxValue=locations[i]+dist[i];
                    nextIndex=i;
                    possible=true;
                }
            }
            else{
                break;
            }
        }
        if(!possible){
            cnt=-1;
            break;
        }
        nidx=nextIndex;
        cnt++;
    }
    cout << cnt << "\n";
}