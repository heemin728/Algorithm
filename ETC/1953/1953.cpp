#include<iostream>
#include<vector>
#include<algorithm>
#include<queue>
#define MAX 101
using namespace std;

int n;
bool arr[MAX][MAX];

int main(){
    cin >> n;
    for(int i=1;i<=n;i++){
        int num=0;
        cin >> num;

        for(int j=0;j<num;j++){
            int a=0;
            cin >> a;
            arr[i][a]=true;
        }
    }

    queue<int> q;

    int check[MAX]={0,};

    int total=n;

    while(total > 0){
        int next=0;

        for(int i=1;i<=n;i++){
            if(check[i]==0){
                next=i;
            }
        }
        check[next]=1;
        q.push(next);
        total--;
        
        while(!q.empty()){
            int index=q.front();
            q.pop();

            for(int j=1;j<=n;j++){
                if(arr[index][j] && check[j]==0){
                    check[j]=check[index]*-1;
                    q.push(j);
                    total--;
                }
            }
        }
    }
    
    int cnt1=0;
    int cnt2=0;

    for(int i=1;i<=n;i++){
        if(check[i]==1){
            cnt1++;
        }
    }
    cout << cnt1 << "\n";

    for(int i=1;i<=n;i++){
        if(check[i]==1){
            cout << i << " ";
        }
    }
    cout << "\n";

    for(int i=1;i<=n;i++){
        if(check[i]<=0){
            cnt2++;
        }
    }
    cout << cnt2 << "\n";

     for(int i=1;i<=n;i++){
        if(check[i]<=0){
            cout << i << " ";
        }
    }
}